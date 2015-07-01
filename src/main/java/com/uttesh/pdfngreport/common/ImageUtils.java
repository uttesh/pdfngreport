/*
 * Copyright 2015 Uttesh Kumar T.H..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uttesh.pdfngreport.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Uttesh Kumar T.H.
 */
public class ImageUtils {

    public static String imageToBase64String(InputStream imageFile) throws Exception {
        String image = null;
        BufferedImage buffImage = ImageIO.read(imageFile);
        if (buffImage != null) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(buffImage, "png", os);
            byte[] data = os.toByteArray();
            image = ImageEncoder.encode(data);
            return "data:image/jpeg;base64,"+image;
        }
        
        return null;
    }
}
