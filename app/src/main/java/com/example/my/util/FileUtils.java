package com.example.my.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

/**
 * 文件操作工具类
 * 对文件操作不要忘记添加下面这个权限：
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 */

public class FileUtils {
    private static final String TAG = "FileUtils";


    public enum FileType {
        JPG, ALL, MP4
    }

    /**
     * 获取某目录下所有给定类型文件集合
     *
     * @param root     文件路径
     * @param fileType 文件类型
     * @return List<File> 集合
     */
    public static List<File> getAllFiles(File root, FileType fileType) {
        List<File> allFiles = new ArrayList<>();
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (!f.isDirectory()) {
                    switch (fileType) {
                        case ALL:
                            allFiles.add(f);
                            break;
                        case JPG:
                            if (getFileExtName(f.getName()).toLowerCase().equals("jpg")) {
                                allFiles.add(f);
                            }
                            break;
                        case MP4:
                            if (getFileExtName(f.getName()).toLowerCase().equals("mp4")) {
                                allFiles.add(f);
                            }
                            break;
                    }
                }
            }
        }
        return allFiles;
    }

    /**
     * 存储是否可写
     *
     * @return boolean
     */
    public static boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取文件名称
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileName(String filePath) {
        String fileName = null;
        if (filePath != null && !filePath.equals("")) {
            fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return fileName;
    }

    /**
     * 获取文件扩展名
     *
     * @param url 文件名或者文件完整路径
     * @return 文件扩展名
     */
    public static String getFileExtName(String url) {
        String fileExtName = null;
        if (url != null && !url.equals("")) {
            fileExtName = url
                    .substring(url.lastIndexOf(".") + 1);
        }
        return fileExtName;
    }

    /**
     * 获取当前应用私有存储位置
     *
     * @param context context
     * @return 存储路径
     * @throws IOException 找不到存储位置
     */
    public static String getAppPath(Context context) throws IOException {
        File[] files = ContextCompat.getExternalFilesDirs(context, null);
        if (files != null && files.length > 0) {
            return files[0].getPath() + "/";
        } else {
            throw new IOException("找不到存储位置");
        }
    }


    private FileOutputStream fos;
    private InputStream is;
    private HttpURLConnection conn;

    public boolean downLoadFile(final String file_url, final String file_savaPath) {

        try {
            File file = new File(file_savaPath);
            if (file.exists()) {
                file.delete();
            }
            URL url = new URL(file_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.connect();
            is = conn.getInputStream();

            fos = new FileOutputStream(file);
            byte buf[] = new byte[512];
            do {
                int numRead = is.read(buf);
                if (numRead <= 0) {
                    break;
                }
                fos.write(buf, 0, numRead);
            } while (true);

            conn.disconnect();
            fos.close();
            is.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.disconnect();
                if (fos != null) {
                    fos.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件拷贝
     *
     * @param sourcesPath 资源路径
     * @param newPath     保存路径
     * @return 保存成功
     */
    public static boolean copyFile(String sourcesPath, String newPath) {
        InputStream inStream = null;
        FileOutputStream fs;
        try {
            int byteRead;
            File sourcesFile = new File(sourcesPath);
            File newFile = new File(newPath);
            if (createDir(newFile.getParent())) {
                if (sourcesFile.exists()) { //文件存在时

                    inStream = new FileInputStream(sourcesPath); //读入原文件
                    fs = new FileOutputStream(newPath);
                    byte[] buffer = new byte[1444];
                    while ((byteRead = inStream.read(buffer)) != -1) {
                        fs.write(buffer, 0, byteRead);
                    }
                    inStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * 创建目录
     *
     * @return 创建是否成功
     */
    private static boolean createDir(String path) {
        if (path != null && !path.equals("")) {
            File file = new File(path);
            if (!file.getName().equals("")) {
                return file.getParentFile().exists() || file.getParentFile().mkdirs();
            } else {
                return file.exists() || file.mkdirs();
            }
        }
        return false;
    }

    /**
     * 创建文件的路径及文件
     *
     * @param path     路径，方法中以默认包含了sdcard的路径，path格式是"/path...."
     * @param filename 文件的名称
     * @return 返回文件的路径，创建失败的话返回为空
     */
    public static String createMkdirsAndFiles(String path, String filename) {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("路径为空");
        }
        path = getSDCardRoot() + path;
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                throw new RuntimeException("创建文件夹不成功");
            }
        }
        File f = new File(file, filename);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("创建文件不成功");
            }
        }
        return f.getAbsolutePath();
    }

    /**
     * 得到sdcard的路径
     *
     * @return
     */
    public static String getSDCardRoot() {
        if (isExternalStorageWritable()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return "";
    }

    /**
     * 获取本地local完整下载路径
     *
     * @param context  context
     * @param fileName 文件名称
     * @return 完整本地local所在路径
     */
    public static String getLocalSavePath_local(Context context, String fileName) {
        String savePath = getProjectRoot(context) + "local/" + fileName;
        if (createDir(savePath)) {
            return savePath;
        }
        return null;
    }

    /**
     * 获取本地文件保存 目录
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 完整本地文件所在路径
     */
    public static String getLocalSavePath_file(Context context, String fileName) {
        String savePath = getProjectRoot(context) + "file/" + fileName;
        if (createDir(savePath)) {
            return savePath;
        }
        return null;
    }

    /**
     * 获取本地日志文件保存 目录
     * 保存在根目录下
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return 完整本地日志文件所在路径
     */
    public static String getLocalSavePath_log(Context context, String fileName) {
        String savePath = getProjectRoot(context) + "log/" + fileName;
        if (createDir(savePath)) {
            return savePath;
        }
        return null;
    }

    /**
     * 获取本地文件保存 目录
     *
     * @param context  上下文
     * @param path     路径，方法中已经默认包含了sdcard的路径，path格式是"path/",相当于getLocalSavePath_log方法中的“log/”
     * @param fileName 文件名
     * @return 完整本地文件所在路径
     */
    public static String getLocalSavePath(Context context, String path, String fileName) {
        String savePath = getProjectRoot(context) + path + fileName;
        if (createDir(savePath)) {
            return savePath;
        }
        return null;
    }

    /**
     * 获取此项目文件存放目录
     * 获取以AppName或者projectName为文件名的文件路径
     * 比如： applicationId "com.example.my"，则在根目录下创建名为My的文件夹
     *
     * @param context 上下文
     * @return 目录
     */
    public static String getProjectRoot(Context context) {
        if (isExternalStorageWritable()) {
            String projectPath = Environment.getExternalStorageDirectory() + "/" + getAppName(context) + "/";
            if (createDir(projectPath)) {
                return projectPath;
            }
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取应用程序名称
     *
     * @param context 上下文
     * @return 应用名称
     */
    private static String getAppName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = context.getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }

    /**
     * 获取uuid作为数据库主键
     *
     * @return uuid
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }


    /**
     * 对文件进行压缩
     *
     * @param file
     * @return
     */
    public static byte[] compressToByte(File file) {
        InputStream inputStream = null;
        ByteArrayOutputStream out;
        GZIPOutputStream gzip = null;
        try {
            inputStream = new FileInputStream(file);
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            byte[] bytes = new byte[1024];
            int count;
            while ((count = inputStream.read(bytes)) > -1) {
                gzip.write(bytes, 0, count);
            }
            gzip.finish();
            gzip.close();
            inputStream.close();
            return out.toByteArray();
        } catch (Exception e) {
            // TODO: handle exception
            e.getStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (gzip != null) {
                    gzip.finish();
                    gzip.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param filePath 需要删除的文件路径
     * @param fileType 需要删除的文件类型
     * @return 删除是否成功
     */
    public static boolean delete(String filePath, FileType fileType) {
        List<File> files;
        File deleteFile;
        deleteFile = new File(filePath);
        files = getAllFiles(deleteFile, fileType);

        if (files != null) {
            for (File file : files) {
                if (!file.delete()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 过滤视频 返回图片url
     *
     * @param urls 图片集合
     * @return
     */
    public static ArrayList<String> removeVideo(ArrayList<String> urls) {
        //视频list集合
        ArrayList<String> url_photos = new ArrayList<>();
        //只保留视频
        for (int i = 0; i < urls.size(); i++) {
            String fileExtName = FileUtils.getFileExtName(urls.get(i));
            if (fileExtName.equals("mp4")) {
                urls.remove(urls.get(i));
            } else {
                url_photos.add(urls.get(i));
            }
        }
        return url_photos;
    }


    /**
     * 创建文件
     *
     * @param filePath 文件地址
     * @param fileName 文件名
     * @return
     */
    public static boolean createFile(String filePath, String fileName) {

        String strFilePath = filePath + fileName;

        File file = new File(filePath);
        if (!file.exists()) {
            /**  注意这里是 mkdirs()方法  可以创建多个文件夹 */
            file.mkdirs();
        }

        File subfile = new File(strFilePath);

        if (!subfile.exists()) {
            try {
                boolean b = subfile.createNewFile();
                return b;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return true;
        }
        return false;
    }

    /**
     * 遍历文件夹下的文件
     *
     * @param file 地址
     */
    public static List<File> getFile(File file) {
        List<File> list = new ArrayList<>();
        File[] fileArray = file.listFiles();
        if (fileArray == null) {
            return null;
        } else {
            for (File f : fileArray) {
                if (f.isFile()) {
                    list.add(0, f);
                } else {
                    getFile(f);
                }
            }
        }
        return list;
    }

    /**
     * 删除文件
     *
     * @param filePath 文件地址
     * @return
     */
    public static boolean deleteFiles(String filePath) {
        List<File> files = getFile(new File(filePath));
        if (files.size() != 0) {
            for (int i = 0; i < files.size(); i++) {
                File file = files.get(i);

                /**  如果是文件则删除  如果都删除可不必判断  */
                if (file.isFile()) {
                    file.delete();
                }

            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("路径为空");
        }
        File file = new File(path);
        if (file.exists()) {
            try {
                file.delete();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 向文件中添加内容
     *
     * @param strcontent 内容
     * @param filePath   地址
     * @param fileName   文件名
     */
    public static void writeToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写

        File subfile = new File(strFilePath);


        RandomAccessFile raf = null;
        try {
            /**   构造函数 第二个是读写方式    */
            raf = new RandomAccessFile(subfile, "rw");
            /**  将记录指针移动到该文件的最后  */
            raf.seek(subfile.length());
            /** 向文件末尾追加内容  */
            raf.write(strcontent.getBytes());

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把内容写入文件
     *
     * @param path 文件路径
     * @param text 内容
     */
    public static void write2File(String path, String text, boolean append) {
        BufferedWriter bw = null;
        try {
            //1.创建流对象
            bw = new BufferedWriter(new FileWriter(path, append));
            //2.写入文件
            bw.write(text);
            //换行刷新
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭流资源
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 修改文件内容（覆盖或者添加）
     *
     * @param path    文件地址
     * @param content 覆盖内容
     * @param append  指定了写入的方式，是覆盖写还是追加写(true=追加)(false=覆盖)
     */
    public static void modifyFile(String path, String content, boolean append) {
        try {
            FileWriter fileWriter = new FileWriter(path, append);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.append(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件内容
     *
     * @param filePath 地址
     * @param filename 名称
     * @return 返回内容
     */
    public static String getString(String filePath, String filename) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File(filePath + filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer("");
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 重命名文件
     *
     * @param oldPath 原来的文件地址
     * @param newPath 新的文件地址
     */
    public static void renameFile(String oldPath, String newPath) {
        File oleFile = new File(oldPath);
        File newFile = new File(newPath);
        //执行重命名
        oleFile.renameTo(newFile);
    }


    /**
     * 复制文件
     *
     * @param fromFile 要复制的文件目录
     * @param toFile   要粘贴的文件目录
     * @return 是否复制成功
     */
    public static boolean copy(String fromFile, String toFile) {
        //要复制的文件目录
        File[] currentFiles;
        File root = new File(fromFile);
        //如同判断SD卡是否存在或者文件是否存在
        //如果不存在则 return出去
        if (!root.exists()) {
            return false;
        }
        //如果存在则获取当前目录下的全部文件 填充数组
        currentFiles = root.listFiles();

        //目标目录
        File targetDir = new File(toFile);
        //创建目录
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }
        //遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
            {
                copy(currentFiles[i].getPath() + "/", toFile + currentFiles[i].getName() + "/");

            } else//如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i].getPath(), toFile + currentFiles[i].getName());
            }
        }
        return true;
    }

    /**
     * 文件拷贝
     * 要复制的目录下的所有非子目录(文件夹)文件拷贝
     *
     * @param fromFile
     * @param toFile
     * @return
     */
    public static boolean CopySdcardFile(String fromFile, String toFile) {

        try {
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
