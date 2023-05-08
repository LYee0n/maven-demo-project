# Build NFS Server Ubuntu

## User
show me how to build nfs server in ubuntu

## ChatGPT


Sure! Here are the steps to set up an NFS (Network File System) server on Ubuntu:

1. Install the necessary packages:
```
sudo apt-get update
sudo apt-get install nfs-kernel-server
```

2. Create the directory to share:
```
sudo mkdir /mnt/nfs_share
```

3. Configure the NFS exports:
```
sudo nano /etc/exports
```

Add the following line to the end of the file to allow access to the NFS share from any client:
```
/mnt/nfs_share    *(rw,sync,no_subtree_check)
```
Save and exit the file.

4. Restart the NFS server:
```
sudo systemctl restart nfs-kernel-server
```

That's it! Your NFS server is now set up and ready to use. Clients can mount the NFS share by specifying the server's IP address and the shared directory path.

For example, to mount the NFS share on a client:
```
sudo mount SERVER_IP:/mnt/nfs_share /mnt/local_mount_point
```

Replace `SERVER_IP` with the IP address of your NFS server, and `/mnt/local_mount_point` with the local directory where you want to access the shared files.

I hope this helps! Let me know if you have any further questions.

## User
how about with ansible

## ChatGPT
Yes, you can also use Ansible to configure an NFS server on Ubuntu. Here's an example playbook to set up an NFS server:

```
- hosts: nfs-server
  become: true

  tasks:
    # Install the NFS server package
    - name: Install NFS server
      apt:
        name: nfs-kernel-server
        state: present

    # Create the directory to share
    - name: Create NFS share directory
      file:
        path: /mnt/nfs_share
        state: directory
        mode: '0777'

    # Configure the NFS exports
    - name: Configure NFS exports
      lineinfile:
        path: /etc/exports
        line: '/mnt/nfs_share    *(rw,sync,no_subtree_check)'

    # Restart the NFS server
    - name: Restart NFS server
      service:
        name: nfs-kernel-server
        state: restarted
```

In this playbook, we install the NFS server package, create the directory to share, configure the NFS exports, and restart the NFS server. The `hosts` section specifies the target host for the playbook, and the `become` option allows us to run the tasks with elevated privileges.

To run the playbook, save it as a file (e.g., `nfs-server.yml`) and use the `ansible-playbook` command:

```
ansible-playbook -i inventory.ini nfs-server.yml
```

Replace `inventory.ini` with your inventory file that lists the target host(s) for the playbook.

I hope this helps! Let me know if you have any further questions.