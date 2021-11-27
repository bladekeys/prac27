package com.company;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;


import static java.lang.System.exit;

public class Editor implements Runnable {
    private String srcFolder = "src/фото ночь прогулка";
    private String dstFolder = "src/result";
    private File srcDir = new File(srcFolder);
    private File[] files = srcDir.listFiles();
    private long start = System.currentTimeMillis();
    private int i;

    public Editor(int i) {
        this.i = i;
    }

    @Override
    public void run(){
        try {
            if (!Files.exists(Paths.get(dstFolder))) {
                Files.createDirectories(Paths.get(dstFolder));
            }
                    BufferedImage image = ImageIO.read(files[i]);
                    if (image == null) {
                        exit(0);
                    }
                    int newWidth = image.getWidth() / 2;
                    int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth)
                    );
                    BufferedImage newImage = new BufferedImage(
                            newWidth, newHeight, BufferedImage.TYPE_INT_RGB
                    );

                    int widthStep = image.getWidth() / newWidth;
                    int heightStep = image.getHeight() / newHeight;

                    for (int x = 0; x < newWidth; x++) {
                        for (int y = 0; y < newHeight; y++) {
                            int rgb = image.getRGB(x * widthStep, y * heightStep);
                            newImage.setRGB(x, y, rgb);
                        }
                    }
                    File newFile = new File(dstFolder + "/" + files[i].getName());
                    ImageIO.write(newImage, "jpg", newFile);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("Duration: " + (System.currentTimeMillis() - start));

    }

}
