package com.locusdiary.ui.module;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * 附件文件过滤类
 * @author Administrator
 *
 */
public class PictureFileFilter extends FileFilter{

	private static final boolean ONE = true;
    private String fileExt;
    private String[] fileExts;
    private boolean type=false;
    private String description;
    private int length;
    private String extension;
    /**
    This is the constructor - it takes in the following:-<br>
    filesExtsIn - this is the array of file extensions that you wish to create a 
    file filter for. <br>
    description - this is  the description that will be displayed in the file
    chooser dialog box.
    */
    public PictureFileFilter(String[] filesExtsIn, String description) 
    {
        if (filesExtsIn.length==1)
        {//we only have one file
            type=ONE;
            fileExt=filesExtsIn[0];
        }
        else
        {
            fileExts=filesExtsIn;
            length = fileExts.length;
        }
        this.description=description;
    }
   /**
   This is the method to allow a file to bee added to the displayed list or not.
    This method is called by the model that handles the FileChooser dialog
    */
    public boolean accept(File f) 
     {
        if (f.isDirectory()) 
        {
          return true;
      }
      extension = getExtension(f);
      if (extension != null) 
      {
            if(type)
                return check(fileExt);
            else
            {
                for(int i=0;i<length;i++)
                {
                    if (check(fileExts[i]))
                        return true;    
                }
            }
       }
       return false;
    }
    /**
    This checks to see if the extension (stored) is the same as the file type
    stored at construction time. The "in" being the value passed over.
    */
    private boolean check(String in)
    {
        return extension.equalsIgnoreCase(in);
    }
   /**
   This is the method defined by the model
    */
    public String getDescription() 
     {
        return description;
    }
    /**
    This is the method to get the file extension from the file name
    */
     private String getExtension(File file) 
     {
      String filename = file.getName();
        int length = filename.length();
      int i = filename.lastIndexOf('.');
        if (i > 0 &&  i < length - 1) 
            return filename.substring(i+1).toLowerCase();
      return null;
   }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog( null , "当前无分类，请先添加分类！");
		
	}

}
