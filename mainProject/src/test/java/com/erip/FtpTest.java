package com.erip;

import static java.lang.String.format;
import static junit.framework.TestCase.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import org.junit.Test;

//define('FTP_USER', 'test');
//define('FTP_USER', 'u32104337');

//define('FTP_PASSV', 'gHV*QlvT7{fe');
//define('FTP_PASSV', 'test');

//define('FTP_SERVER', 'mingasru.418.com1.ru');
//define('FTP_USER', 'erip@mingasru.418.com1.ru');
//    define('FTP_PASSV', 'VKsmQKgv');

//define('FTP_SERVER', '10.54.1.130');
//    define('FTP_SERVER', '10.54.1.132');

//# FTP доступ:
//    Хост: inyourbodyru.404.com1.ru
//    Имя пользователя: inyourb4
//    Пароль: d35fnpo3R


public class FtpTest {
    private final String login = "inyourb4";
    private final String password = "d35fnpo3R";
    private final String host = "inyourbodyru.404.com1.ru";
    @Test
    public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem() throws IOException {

        String ftpUrl = format("ftp://%s:%s@%s/tmp/foobar.txt", login, password, host);

        URLConnection urlConnection = new URL(ftpUrl).openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        Files.copy(inputStream, new File("downloaded_buz.txt").toPath());
        inputStream.close();

        assertTrue(new File("downloaded_buz.txt").exists());
        new File("downloaded_buz.txt").delete(); // cleanup
    }
}
