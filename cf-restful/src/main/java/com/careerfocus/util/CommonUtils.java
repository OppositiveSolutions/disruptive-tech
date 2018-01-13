package com.careerfocus.util;

import com.careerfocus.util.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class CommonUtils {

    private static Logger log = LoggerFactory.getLogger(CommonUtils.class.getClass());

    public static void setUnauthorizedResponse(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        Response unAuthorizedResponse = Response.unauthorized().build();
        try {
            PrintWriter out = response.getWriter();
            out.println(unAuthorizedResponse.toJsonString());
        } catch (IOException e) {
            log.error("Error", e);
        }
    }

    public static String getImageTypeFromByteArray(byte[] data) {
        String imageType = "jpeg";
        try {
            ImageInputStream iis = ImageIO
                    .createImageInputStream(new ByteArrayInputStream(data));

            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);

            if (readers.hasNext()) {
                ImageReader read = readers.next();
                imageType = read.getFormatName().toLowerCase();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageType;
    }

}
