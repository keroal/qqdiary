package com.qqdiary.app.ui;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;


public class store extends JFrame {
/**
* @param args
*/
public static void main(String[] args) throws Exception {
new ConfigFrame("保存文件");
}
}
class ConfigFrame extends JFrame {
private static final long serialVersionUID = 1L;
public ConfigFrame(String title) {
// 设置窗口属性
final int width = 300;
final int height = 200;
final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
final int left = (screen.width - width) / 2;
final int top = (screen.height - height) / 2;
this.setLocation(left, top);
this.setSize(width, height);
this.setTitle(title);
// 添加组件
JPanel panel = new JPanel();
this.add(panel);
JButton b = new JButton("打开");
panel.add(b);
b.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
JFileChooser chooser = new JFileChooser(".");
TxtFileFilter txtFilter = new TxtFileFilter();
JavaFileFilter javaFilter = new JavaFileFilter();
chooser.addChoosableFileFilter(txtFilter);
chooser.addChoosableFileFilter(javaFilter);
// 设置默认的文件管理器。如果不设置,则最后添加的文件过滤器为默认过滤器,本例中为javaFilter
chooser.setFileFilter(txtFilter);
int rs = chooser.showSaveDialog(ConfigFrame.this);
}
});
this.setVisible(true);
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
class JavaFileFilter extends FileFilter {
public String getDescription() {
return "*.java(java源文件)";
}
public boolean accept(File file) {
String name = file.getName();
return name.toLowerCase().endsWith(".java");
}
}
class TxtFileFilter extends FileFilter {
public String getDescription() {
return "*.txt(文本文件)";
}
public boolean accept(File file) {
String name = file.getName();
return name.toLowerCase().endsWith(".txt");
}
}