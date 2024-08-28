package com.sparta.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JCodecService {
    private static final String EXTENSION = "png";
    private final FileService fileService; // FileService 주입

    public String getThumbnail(File source) throws IOException, JCodecException {
        File tempThumbnail = File.createTempFile("thumbnail_", "." + EXTENSION);
        try {
            FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
            frameGrab.seekToSecondPrecise(0);
            Picture picture = frameGrab.getNativeFrame();
            BufferedImage bi = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bi, EXTENSION, tempThumbnail);
        } catch (Exception e) {
            return null;
        }
        return fileService.uploadFile(FileService.THUMBNAIL_UPLOAD_DIR,FileService.THUMBNAIL_URL_DIR, tempThumbnail);
    }

    public static Long getDuration(File source) throws IOException, JCodecException {
        FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
        double durationInSeconds = frameGrab.getVideoTrack().getMeta().getTotalDuration();
        log.info("Video length: {} seconds", durationInSeconds);
        return (long) durationInSeconds;

    }
}