package com.locusdiary.ui.module;

import java.awt.image.BufferedImage;    
import java.awt.geom.AffineTransform;  
import java.awt.image.AffineTransformOp;  
import java.io.File;  
import java.io.IOException;  
import javax.imageio.ImageIO;  
  
public class Thumbnail {   
   
     
        public static BufferedImage zoomImage(String path, int with, int height){   
               
                File file = new File(path); 
                AffineTransform transform = new AffineTransform();  

                BufferedImage bsrc = null;
                BufferedImage bsmall = null;
                
                try {   
                	bsrc = ImageIO.read(file);   
                	transform.setToScale(0.5,0.5);
                	AffineTransformOp ato = new AffineTransformOp(transform,null); 
                	bsmall = new BufferedImage(with,height,BufferedImage.TYPE_3BYTE_BGR);  
                	ato.filter(bsrc,bsmall);   
                }catch (IOException ex) {   
                      
                }
                
                return bsmall;
        }  
}  
