package com.connectsdk.service.webos.lgcast.common.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtil {
    public static int read(InputStream inputStream, byte[] buffer, int offset, int length) {
        try {
            if (inputStream != null) {
                if (buffer == null) {
                    throw new IOException();
                }
                return inputStream.read(buffer, offset, length);
            }
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String readLine2(final InputStream input) throws Exception {
        try {
            if (input == null) {
                throw new Exception("Invalid arguments");
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1];
            while (input.read(bArr) > 0) {
                if (bArr[0] != 13) {
                    if (bArr[0] == 10) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr[0]);
                }
            }
            if (byteArrayOutputStream.size() > 0) {
                return byteArrayOutputStream.toString();
            }
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    public static String readString(final InputStream input, final long length) throws IOException {
        if (input == null || length < 0) {
            throw new IOException("Invalid arguments");
        }
        byte[] readData = readData(input, length);
        if (readData != null) {
            return new String(readData);
        }
        return null;
    }

    public static byte[] readData(final InputStream input, final long length) throws IOException {
        if (input == null || length < 0) {
            throw new IOException("Invalid arguments");
        }
        byte[] bArr = new byte[4096];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long j = 0;
        while (j < length) {
            long read = input.read(bArr, 0, (int) Math.min(length - j, 4096));
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, (int) read);
            j += read;
        }
        return byteArrayOutputStream.size() > 0 ? byteArrayOutputStream.toByteArray() : new byte[0];
    }

    public static void writeString(final OutputStream output, final String string) throws IOException {
        writeData(output, string != null ? string.getBytes() : null);
    }

    public static void writeData(final OutputStream output, final byte[] data) throws IOException {
        if (output == null) {
            throw new IOException("Invalid stream");
        }
        if (data != null && data.length > 0) {
            output.write(data, 0, data.length);
            output.flush();
        }
    }

    public static long copy(final String inputPath, final String outputPath) throws IOException {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            if (inputPath == null || outputPath == null) {
                throw new IOException("Invalid file");
            }
            FileInputStream fileInputStream2 = new FileInputStream(inputPath);
            try {
                fileOutputStream = new FileOutputStream(outputPath);
                try {
                    long copy = copy(fileInputStream2, fileOutputStream);
                    close(fileInputStream2);
                    close(fileOutputStream);
                    return copy;
                } catch (IOException e) {
                    e = e;
                    fileInputStream = fileInputStream2;
                    try {
                        e.printStackTrace();
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        close(fileInputStream);
                        close(fileOutputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    fileInputStream = fileInputStream2;
                    close(fileInputStream);
                    close(fileOutputStream);
                    throw th2;
                }
            } catch (Throwable e2) {
                fileOutputStream = null;
            }
        } catch (Throwable e3) {
            fileOutputStream = null;
        }
        return 0;
    }

    public static long copy(final InputStream input, final OutputStream output) throws IOException {
        return copy(input, output, 2147483647L);
    }

    public static long copy(final InputStream input, final OutputStream output, long length) throws IOException {
        if (input == null || output == null) {
            throw new IOException("Invalid stream");
        }
        byte[] bArr = new byte[8192];
        long j = 0;
        do {
            int read = input.read(bArr, 0, (int) Math.min(8192, length));
            if (read <= 0) {
                break;
            }
            output.write(bArr, 0, read);
            length -= read;
            j += read;
        } while (length > 0);
        return j;
    }

    public static void close(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readRawResourceText(Context context, int rawResId) {
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            byte[] bArr = new byte[128];
            inputStream = context != null ? context.getResources().openRawResource(rawResId) : null;
            try {
                if (inputStream == null) {
                    throw new Exception("Invalid resource");
                }
                int read = inputStream.read(bArr, 0, 128);
                String str = read > 0 ? new String(bArr, 0, read) : null;
                String trim = str != null ? str.trim() : null;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                return trim;
            } catch (Exception unused2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                inputStream2 = inputStream;
                th = th;
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Exception unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            inputStream = null;
        } catch (Throwable th2) {
        }
        return null;
    }
}
