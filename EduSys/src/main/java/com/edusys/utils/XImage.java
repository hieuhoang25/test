/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author HieuHoang
 */
public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("/com/edusys/icons/fpt.png");
        return new ImageIcon(url).getImage();
    }
    
    public static  void save(File src){
        try {
            File dst = new File("logos", src.getName());
            if (!dst.getParentFile().exists()){
                // tạo thư mục logos nếu chưa tồn tại
                dst.getParentFile().mkdir();
            }
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
           // copy file vào thư mục logos
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    public static ImageIcon read(String fileName){
        File path = new File("logos", fileName);
        return new ImageIcon(path.getAbsolutePath());
    }
}
