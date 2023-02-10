# Batch FTP CLI Command
**You**: 
do you know there is batch ftp cli command tool?

--------
**ChatGPT**: Yes, there are several command line tools for performing batch FTP transfers. Some commonly used ones include:```lftp```: a powerful and user-friendly command line FTP client```ncftp```: a shell-like FTP client with advanced features```curl```: a command line tool for transferring data with URL syntax, which supports FTP among other protocols

These tools allow you to automate FTP transfers and perform actions such as uploading and downloading multiple files, synchronizing directories, and managing remote directories from the command line.

--------
**You**: show me how to use lftp example

--------
**ChatGPT**: Here is an example of using ```lftp``` to connect to an FTP server, download a file, and then disconnect:sql
```sql
$ lftp ftp.example.com
lftp ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; get filename.txt
lftp username@ftp.example.com:~&gt; quit
```

In this example, the first line connects to the FTP server ```ftp.example.com```. The ```user``` command is used to log in with the username ```username``` and password ```password```. The ```get``` command is used to download the file ```filename.txt``` from the server, and the ```quit``` command is used to disconnect from the server.

You can also use ```lftp``` to automate transfers by putting the commands in a script file and executing the script. For example:bash
```bash
#!/bin/sh

lftp ftp.example.com &lt;&lt;EOF
user username password
get filename.txt
quit
EOF
```

This script logs into the FTP server, downloads the file ```filename.txt```, and then disconnects. You can save the script to a file with the ```.sh``` extension and make it executable with ```chmod +x script.sh```, then run it by typing ```./script.sh```.

--------
**You**: how to upload or download multiple files?

--------
**ChatGPT**: To upload or download multiple files using ```lftp```, you can use the ```mget``` and ```mput``` commands.

Here's an example of uploading multiple files to the FTP server:sql
```sql
lftp ftp.example.com
lftp ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; mput file1.txt file2.txt file3.txt
lftp username@ftp.example.com:~&gt; quit
```

In this example, the ```mput``` command is used to upload the files ```file1.txt```, ```file2.txt```, and ```file3.txt``` to the FTP server.

And here's an example of downloading multiple files from the FTP server:sql
```sql
lftp ftp.example.com
lftp ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; mget file1.txt file2.txt file3.txt
lftp username@ftp.example.com:~&gt; quit
```

In this example, the ```mget``` command is used to download the files ```file1.txt```, ```file2.txt```, and ```file3.txt``` from the FTP server.

Note that you can use wildcard characters to download or upload multiple files that match a certain pattern. For example, ```mget *.txt``` will download all ```.txt``` files in the current directory.

--------
**You**: how to do this without interacitve

--------
**ChatGPT**: To automate FTP transfers without using the interactive mode in ```lftp```, you can use a script file that contains the necessary commands. Here's an example of a script that uploads multiple files to the FTP server:bash
```bash
#!/bin/sh

lftp -u username,password ftp.example.com &lt;&lt;EOF
mput file1.txt file2.txt file3.txt
quit
EOF
```

In this script, the ```-u``` option is used to specify the username and password for the FTP server, followed by the server's hostname or IP address. The ```mput``` command is used to upload the files ```file1.txt```, ```file2.txt```, and ```file3.txt``` to the FTP server, and the ```quit``` command is used to disconnect from the server.

Here's an example of a script that downloads multiple files from the FTP server:bash
```bash
#!/bin/sh

lftp -u username,password ftp.example.com &lt;&lt;EOF
mget file1.txt file2.txt file3.txt
quit
EOF
```

In this script, the ```mget``` command is used to download the files ```file1.txt```, ```file2.txt```, and ```file3.txt``` from the FTP server.

Save the script to a file with the ```.sh``` extension, make it executable with ```chmod +x script.sh```, then run it by typing ```./script.sh```.

--------
**You**: is lftp support ftp and ftps and sftp?

--------
**ChatGPT**: Yes, ```lftp``` supports FTP (File Transfer Protocol), FTPS (FTP over SSL/TLS), and SFTP (SSH File Transfer Protocol).

To connect to an FTP server using ```lftp```, simply specify the server's hostname or IP address, as shown in the previous examples.

To connect to an FTPS server, use the ```ftps``` protocol instead of ```ftp``` when connecting to the server:kotlin
```kotlin
lftp ftps://ftp.example.com
lftp ftps://ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; get filename.txt
lftp username@ftp.example.com:~&gt; quit
```

To connect to an SFTP server, use the ```sftp``` protocol instead of ```ftp``` when connecting to the server:kotlin
```kotlin
lftp sftp://ftp.example.com
lftp sftp://ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; get filename.txt
lftp username@ftp.example.com:~&gt; quit
```

In both the FTPS and SFTP examples, the rest of the commands are the same as for FTP.

--------
**You**: how to spec port?

--------
**ChatGPT**: To specify a port when connecting to an FTP, FTPS, or SFTP server using ```lftp```, you can use the ```-p``` option, followed by the port number.

Here's an example of connecting to an FTP server on a non-standard port:sql
```sql
lftp -p 21 ftp.example.com
lftp ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; get filename.txt
lftp username@ftp.example.com:~&gt; quit
```

In this example, the ```-p 21``` option is used to specify the port number for the FTP server.

Here's an example of connecting to an FTPS server on a non-standard port:kotlin
```kotlin
lftp -p 990 ftps://ftp.example.com
lftp ftps://ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; get filename.txt
lftp username@ftp.example.com:~&gt; quit
```

And here's an example of connecting to an SFTP server on a non-standard port:kotlin
```kotlin
lftp -p 22 sftp://ftp.example.com
lftp sftp://ftp.example.com:~&gt; user username password
lftp username@ftp.example.com:~&gt; get filename.txt
lftp username@ftp.example.com:~&gt; quit
```

Note that the default port for FTP is 21, the default port for FTPS is 990, and the default port for SFTP is 22. If the server you're connecting to uses a different port, you'll need to specify it using the ```-p``` option.

