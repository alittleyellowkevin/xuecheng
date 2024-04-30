package com.kevin.media;

import javafx.print.Collation;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.M
 * @version 1.0
 * @description 测试大文件上传方法
 * @date 2023/2/18 9:24
 */
public class BigFileTest {

    //分块测试
    @Test
    public void testChunk() throws IOException {
        //源文件
        File sourceFile = new File("/Users/huangkaiwen/Downloads/v0d00fg10000co8i6src77ublvd820q0.MP4");
        //分块文件存储路径
        String chunkFilePath = "/Users/huangkaiwen/Downloads/chunk/";
        int chunkSize = 1024 * 1024 * 5; // 1MB
        //分块文件个数
        int chunkNum = (int) Math.ceil(sourceFile.length() * 1.0 / chunkSize);
        //使用流从源文件读数据，向分块文件中写数据
        RandomAccessFile raf_r = new RandomAccessFile(sourceFile, "r");
        //缓存区
        byte[] buffer = new byte[chunkSize];

        for (int i = 0; i < chunkNum; i++) {
            File chunkFile = new File(chunkFilePath + i);
            //分块文件写入流
            RandomAccessFile raf_rw = new RandomAccessFile(chunkFile, "rw");
            int bytesRead;
            int totalBytesRead = 0;
            while (totalBytesRead < chunkSize && (bytesRead = raf_r.read(
                    buffer,
                    0,
                    Math.min(buffer.length, chunkSize - totalBytesRead))) != -1) {
                raf_rw.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            raf_rw.close();
        }
        raf_r.close();
    }

    //将分块进行合并
    @Test
    public void testMerge() throws IOException {
        //块文件目录
        File chunkFolder = new File("/Users/huangkaiwen/Downloads/chunk/");
        //源文件、
        File sourceFile = new File("/Users/huangkaiwen/Downloads/v0d00fg10000co8i6src77ublvd820q0.MP4");
        //合并后的文件
        File mergeFile = new File("/Users/huangkaiwen/Downloads/test.MP4");

        //取出所有分块文件
        File[] files = chunkFolder.listFiles();
        List<File> filesList = Arrays.stream(files)
                .filter(file -> file.getName().matches("\\d+"))  // 只包含数字的文件名
                .collect(Collectors.toList());

        //对分块文件排序
        Collections.sort(filesList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return Integer.parseInt(o1.getName())-Integer.parseInt(o2.getName());
            }
        });
        //向合并文件写的流
        RandomAccessFile raf_rw = new RandomAccessFile(mergeFile, "rw");
        //缓存区
        byte[] bytes = new byte[1024];
        //遍历分块文件，向合并 的文件写
        for (File file : filesList) {
            //读分块的流
            RandomAccessFile raf_r = new RandomAccessFile(file, "r");
            int len = -1;
            while ((len=raf_r.read(bytes))!=-1){
                raf_rw.write(bytes,0,len);
            }
            raf_r.close();

        }
        raf_rw.close();
        //合并文件完成后对合并的文件md5校验
        FileInputStream fileInputStream_merge = new FileInputStream(mergeFile);
        FileInputStream fileInputStream_source = new FileInputStream(sourceFile);
        String md5_merge = DigestUtils.md5Hex(fileInputStream_merge);
        String md5_source = DigestUtils.md5Hex(fileInputStream_source);
        if(md5_merge.equals(md5_source)){
            System.out.println("文件合并成功");
        }

    }


}
