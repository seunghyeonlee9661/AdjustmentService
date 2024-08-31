package com.sparta.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class JCodecService {
    private static final Logger logger = LoggerFactory.getLogger(JCodecService.class);
    private static final String EXTENSION = "png";
    private final FileService fileService; // FileService 주입

    public String getThumbnail(File source) throws IOException {
        File tempThumbnail = File.createTempFile("thumbnail_", "." + EXTENSION);
        try{
            FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
            frameGrab.seekToSecondPrecise(0);
            Picture picture = frameGrab.getNativeFrame();
            BufferedImage bi = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bi, EXTENSION, tempThumbnail);
            return fileService.uploadFile(FileService.THUMBNAIL_UPLOAD_DIR,FileService.THUMBNAIL_URL_DIR, tempThumbnail);
        }
        catch (JCodecException e) {
             tempThumbnail.delete();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 파일 유형입니다.", e);
        }
    }

    public static Long getDuration(File source) throws IOException {
        try{
            FrameGrab frameGrab = FrameGrab.createFrameGrab(NIOUtils.readableChannel(source));
            double durationInSeconds = frameGrab.getVideoTrack().getMeta().getTotalDuration();
            log.info("Video length: {} seconds", durationInSeconds);
            return (long) durationInSeconds;
        }
        catch (JCodecException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "올바르지 않은 파일 유형입니다.", e);
        }
    }
}
