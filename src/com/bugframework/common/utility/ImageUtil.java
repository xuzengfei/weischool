package com.bugframework.common.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;


/**
 * <p>Title: ImageUtil </p>
 * <p>Description: 使用JDK原生态类生成图片缩略图和裁剪图片 </p>
 * <p>Email: icerainsoft@hotmail.com </p>
 *
 * @author Ares
 * @date 2014年10月28日 上午10:24:26
 */
public class ImageUtil {


    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = true;

    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     *
     * @param imagePath 原图片路径
     * @param w         缩略图宽
     * @param h         缩略图高
     * @param prevfix   生成缩略图的前缀
     * @param force     是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public void thumbnailImage(File imgFile, int w, int h, String prevfix, boolean force) {
        if (imgFile.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if (imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    System.out.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return;
                }
                System.out.println("target image's size, width:{}, height:{}." + w + h);
                Image img = ImageIO.read(imgFile);
                if (!force) {
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / w < (height * 1.0) / h) {
                        if (width > w) {
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                            System.out.println("change image's height, width:{}, height:{}." + w + h);
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                            System.out.println("change image's width, width:{}, height:{}." + w + h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀
                ImageIO.write(bi, suffix, new File(p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix + imgFile.getName()));
            } catch (IOException e) {
                System.out.println("generate thumbnail image failed." + e);
            }
        } else {
            System.out.println("the image is not exist.");
        }
    }
    
    public static  void  thumbImage(File imgFile, int w, int h, String thumbPath) {
        if (imgFile.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if (imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    System.out.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return;
                }
              
                Image img = ImageIO.read(imgFile);
              
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀
                ImageIO.write(bi, suffix, new File(thumbPath));
            } catch (IOException e) {
                System.out.println("generate thumbnail image failed." + e);
            }
        } else {
            System.out.println("the image is not exist.");
        }
    }

    public String compressImage(File imgFile, int w, int h, String prevfix, boolean force) {
        String filePath = null;
        if (imgFile.exists()) {
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if (imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if (suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0) {
                    System.out.println("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
                    return null;
                }
                Image img = ImageIO.read(imgFile);
                if (!force) {
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if ((width * 1.0) / w < (height * 1.0) / h) {
                        if (width > w) {
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w / (width * 1.0)));
                        }
                    } else {
                        if (height > h) {
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h / (height * 1.0)));
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀
                filePath = p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix + imgFile.getName();
                ImageIO.write(bi, suffix, new File(filePath));

            } catch (IOException e) {
                System.out.println("generate thumbnail image failed." + e);
            }
            return filePath;
        } else {
            System.out.println("the image is not exist.");
            return null;
        }
    }

    public void thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force) {
        File imgFile = new File(imagePath);
        thumbnailImage(imgFile, w, h, prevfix, force);
    }

    public void thumbnailImage(String imagePath, int w, int h, boolean force) {
        thumbnailImage(imagePath, w, h, DEFAULT_PREVFIX, force);
    }

    public void thumbnailImage(String imagePath, int w, int h) {
        thumbnailImage(imagePath, w, h, DEFAULT_FORCE);
    }

}