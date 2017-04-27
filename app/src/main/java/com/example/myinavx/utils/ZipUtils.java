package com.example.myinavx.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by anna on 2017/4/20.
 */

public class ZipUtils {

    public static void unzipFile(String zipFileName, String unzipFilePath) {

        ZipInputStream inZip = null;
        try {
            inZip = new ZipInputStream(new FileInputStream(zipFileName));
            ZipEntry zipEntry;

            while ((zipEntry = inZip.getNextEntry()) != null) {
                String entryName = zipEntry.getName();

                if (zipEntry.isDirectory()) {

                    // get the folder name of the widget
                    entryName = entryName.substring(0, entryName.length() - 1);
                    File folder = new File(unzipFilePath + File.separator + entryName);
                    folder.mkdirs();

                } else {

                    File file = new File(unzipFilePath + File.separator + entryName);
                    file.createNewFile();
                    // get the output stream of thße file
                    FileOutputStream out = new FileOutputStream(file);
                    int len;
                    byte[] buffer = new byte[1024];
                    // read (len) bytes into buffer
                    while ((len = inZip.read(buffer)) != -1) {
                        // write (len) byte from buffer at the position 0
                        out.write(buffer, 0, len);
                        out.flush();
                    }
                    out.close();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inZip != null) {
                try {
                    inZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void zipFiles(String srcFileName, String zipFileName) {

        ZipOutputStream outZip = null;
        try {
            outZip = new ZipOutputStream(new FileOutputStream(zipFileName));
            File file = new File(srcFileName);

            zipFiles(file.getParent() + File.separator, file.getName(), outZip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outZip != null) {
                try {
                    outZip.finish();
                    outZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void zipFiles(String folderName, String fileName, ZipOutputStream zipOutputSteam) throws Exception {

        if (zipOutputSteam == null)
            return;

        File file = new File(folderName + fileName);

        if (file.isFile()) {
            ZipEntry zipEntry = new ZipEntry(fileName);
            FileInputStream inputStream = new FileInputStream(file);
            zipOutputSteam.putNextEntry(zipEntry);

            int len;
            byte[] buffer = new byte[4096];

            while ((len = inputStream.read(buffer)) != -1) {
                zipOutputSteam.write(buffer, 0, len);
            }

            zipOutputSteam.closeEntry();
        } else {
            String fileList[] = file.list();

            if (fileList.length <= 0) {
                ZipEntry zipEntry = new ZipEntry(fileName + File.separator);
                zipOutputSteam.putNextEntry(zipEntry);
                zipOutputSteam.closeEntry();
            }

            for (int i = 0; i < fileList.length; i++) {
                zipFiles(folderName, fileName + File.separator + fileList[i], zipOutputSteam);
            }
        }
    }

    public static InputStream upzipFile(String zipFileName, String unzipFileName) throws Exception {

        ZipFile zipFile = new ZipFile(zipFileName);
        ZipEntry zipEntry = zipFile.getEntry(unzipFileName);

        return zipFile.getInputStream(zipEntry);
    }

    public static List<File> getFileList(String zipFileName, boolean containFolder, boolean containFile) {

        List<java.io.File> fileList = new ArrayList<File>();

        ZipInputStream inZip = null;
        try {
            inZip = new ZipInputStream(new FileInputStream(zipFileName));

            ZipEntry zipEntry;

            while (null != (zipEntry = inZip.getNextEntry())) {

                String entryName = zipEntry.getName();

                if (zipEntry.isDirectory()) {
                    // get the folder name of the widget
                    entryName = entryName.substring(0, entryName.length() - 1);
                    File folder = new File(entryName);
                    if (containFolder) {
                        fileList.add(folder);
                    }

                } else {
                    File file = new File(entryName);
                    if (containFile) {
                        fileList.add(file);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inZip != null) {
                try {
                    inZip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return fileList;
    }

}
