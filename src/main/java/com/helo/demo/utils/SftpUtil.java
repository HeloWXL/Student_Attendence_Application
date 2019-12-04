package com.helo.demo.utils;

import com.jcraft.jsch.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @Classname SftpUtil
 * @Description TODO
 * @Date 2019/12/4 9:32 下午
 * @Created by wangxianlin
 */
public class SftpUtil {
    private static final Logger LOGGER = LogManager.getLogger(SftpUtil.class);
    private static final String FTP_HOST_IP = ConfigUtil.getValue("ftp.host");
    private static final int FTP_PORT = Integer.parseInt(ConfigUtil.getValue("ftp.port"));
    private static final String USER_NAME = ConfigUtil.getValue("ftp.username");
    private static final String PASSWORD = ConfigUtil.getValue("ftp.password");
    private static final int TIME_OUT = 5000;

    public SftpUtil() {
    }

    public static ChannelSftp getSftpClient() {
        ChannelSftp sftp = null;

        try {
            JSch jsch = new JSch();
            Session sshSession = jsch.getSession(USER_NAME, FTP_HOST_IP, FTP_PORT);
            sshSession.setPassword(PASSWORD);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.setTimeout(5000);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp)channel;
            LOGGER.info("Connected to " + FTP_HOST_IP + ".");
        } catch (Exception var5) {
            LOGGER.catching(var5);
        }

        return sftp;
    }

    private static void cdDir(String dir, ChannelSftp sftp) {
        try {
            SftpATTRS sftpATTRS = sftp.lstat(dir);
            boolean isDir = sftpATTRS.isDir();
            if (isDir) {
                LOGGER.info("目录已存在{}", isDir);
                sftp.cd(dir);
            } else {
                LOGGER.error("不是目录", isDir);
            }
        } catch (SftpException var5) {
            LOGGER.error("创建目录不存在，创建此目录..", var5);

            try {
                sftp.mkdir(dir);
                sftp.cd(dir);
            } catch (SftpException var4) {
                LOGGER.error("创建目录失败", var4);
            }
        }

    }

    public static boolean upload(InputStream fis, String dir, String newFileName) {
        ChannelSftp sftp = getSftpClient();
        boolean flag = true;
        boolean var13 = false;

        Session sftpSession;
        label112: {
            try {
                var13 = true;
                cdDir(dir, sftp);
                sftp.put(fis, newFileName);
                var13 = false;
                break label112;
            } catch (Exception var17) {
                LOGGER.info("sftp文件上传失败。。。");
                flag = false;
                LOGGER.catching(var17);
                var13 = false;
            } finally {
                if (var13) {
                    try {
                         sftpSession = sftp.getSession();
                        if (sftp.isConnected()) {
                            sftp.disconnect();
                        }

                        if (sftpSession != null) {
                            sftpSession.disconnect();
                        }
                    } catch (JSchException var14) {
                        LOGGER.catching(var14);
                    }

                }
            }

            try {
                sftpSession = sftp.getSession();
                if (sftp.isConnected()) {
                    sftp.disconnect();
                }

                if (sftpSession != null) {
                    sftpSession.disconnect();
                    return flag;
                }
            } catch (JSchException var15) {
                LOGGER.catching(var15);
            }

            return flag;
        }

        try {
            sftpSession = sftp.getSession();
            if (sftp.isConnected()) {
                sftp.disconnect();
            }

            if (sftpSession != null) {
                sftpSession.disconnect();
            }
        } catch (JSchException var16) {
            LOGGER.catching(var16);
        }

        return flag;
    }

    public static boolean download(OutputStream os, String fileName) {
        ChannelSftp sftp = getSftpClient();
        boolean flag = true;
        boolean var12 = false;

        Session sftpSession;
        label112: {
            try {
                var12 = true;
                sftp.get(fileName, os);
                var12 = false;
                break label112;
            } catch (Exception var16) {
                LOGGER.info("sftp文件下载失败。。。");
                flag = false;
                LOGGER.catching(var16);
                var12 = false;
            } finally {
                if (var12) {
                    try {
                        sftpSession = sftp.getSession();
                        if (sftp.isConnected()) {
                            sftp.disconnect();
                        }

                        if (sftpSession != null) {
                            sftpSession.disconnect();
                        }
                    } catch (JSchException var13) {
                        LOGGER.catching(var13);
                    }

                }
            }

            try {
                sftpSession = sftp.getSession();
                if (sftp.isConnected()) {
                    sftp.disconnect();
                }

                if (sftpSession != null) {
                    sftpSession.disconnect();
                    return flag;
                }
            } catch (JSchException var14) {
                LOGGER.catching(var14);
            }

            return flag;
        }

        try {
            sftpSession = sftp.getSession();
            if (sftp.isConnected()) {
                sftp.disconnect();
            }

            if (sftpSession != null) {
                sftpSession.disconnect();
            }
        } catch (JSchException var15) {
            LOGGER.catching(var15);
        }

        return flag;
    }

    public static boolean deleteFile(String fileName) {
        ChannelSftp sftp = getSftpClient();
        boolean flag = true;
        boolean var11 = false;

        Session sftpSession;
        label112: {
            try {
                var11 = true;
                sftp.rm(fileName);
                var11 = false;
                break label112;
            } catch (Exception var15) {
                LOGGER.info("sftp文件删除失败。。。");
                flag = false;
                LOGGER.catching(var15);
                var11 = false;
            } finally {
                if (var11) {
                    try {
                       sftpSession = sftp.getSession();
                        if (sftp.isConnected()) {
                            sftp.disconnect();
                        }

                        if (sftpSession != null) {
                            sftpSession.disconnect();
                        }
                    } catch (JSchException var12) {
                        LOGGER.catching(var12);
                    }

                }
            }

            try {
                sftpSession = sftp.getSession();
                if (sftp.isConnected()) {
                    sftp.disconnect();
                }

                if (sftpSession != null) {
                    sftpSession.disconnect();
                    return flag;
                }
            } catch (JSchException var13) {
                LOGGER.catching(var13);
            }

            return flag;
        }

        try {
            sftpSession = sftp.getSession();
            if (sftp.isConnected()) {
                sftp.disconnect();
            }

            if (sftpSession != null) {
                sftpSession.disconnect();
            }
        } catch (JSchException var14) {
            LOGGER.catching(var14);
        }

        return flag;
    }
}
