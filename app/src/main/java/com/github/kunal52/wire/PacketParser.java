package com.github.kunal52.wire;

import java.io.IOException;
import java.io.InputStream;

public abstract class PacketParser extends Thread {
    private final InputStream mInputStream;
    private volatile boolean isAbort = false;

    public PacketParser(InputStream inputStream) {
        this.mInputStream = inputStream;
    }

    public abstract void messageBufferReceived(byte[] bArr);

/*
    @Override
    public void run() {
        while (true) {
            int i = 0;
            while (true) {
                if (!this.isAbort) {
                    try {
                        int read = this.mInputStream.read();
                        byte[] bArr = new byte[read];
                        while (i < read) {
                            int read2 = this.mInputStream.read(bArr, i, read - i);
                            if (read2 >= 0) {
                                i += read2;
                            } else {
                                throw new IOException("Stream closed while reading.");
                            }
                        }
                        try {
                            messageBufferReceived(bArr);
                        } catch (Exception unused) {
                            i = 0;
                        }
                    } catch (Exception unused2) {
                    }
                } else {
                    return;
                }
                this.isAbort = true;
            }
        }
    }
*/

/*
    @Override
    public void run() {
        try {
            int available;
            int bytesRead = 0;
            while (!isAbort)
                try {
                    available = mInputStream.read();
                    byte[] buf = new byte[available];
                    if (buf.length > 0) {
                        while (bytesRead < available) {
                            int read = mInputStream.read(buf, bytesRead, available - bytesRead);
                            if (read < 0) {
                                throw new IOException("Stream closed while reading.");
                            }
                            bytesRead += read;
                        }

                        bytesRead = 0;
                        messageBufferReceived(buf);
                    }
                } catch (IOException e) {
                    isAbort = true;
//                throw new RuntimeException(e);
                    e.printStackTrace();
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    @Override
    public void run() {
        try {
            int available;
            int bytesRead = 0;
            while (!isAbort) {
                try {
                    available = mInputStream.read();
                    if (available == -1) {
                        // End of stream, exit the loop
                        break;
                    }
                    byte[] buf = new byte[available];
                    if (buf.length > 0) {
                        while (bytesRead < available) {
                            int read = mInputStream.read(buf, bytesRead, available - bytesRead);
                            if (read < 0) {
                                throw new IOException("Stream closed while reading.");
                            }
                            bytesRead += read;
                        }

                        bytesRead = 0;
                        messageBufferReceived(buf);
                    }
                } catch (IOException e) {
                    isAbort = true;
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abort() {
        this.isAbort = true;
    }

}
