**You**: do you know how to ansible generate multiple text file with same template?

--------
**ChatGPT**: Yes, Ansible can generate multiple text files with the same template using the ```template``` module and a loop in the playbook.

Here is an example playbook that demonstrates how to use the ```template``` module to generate multiple text files from the same template:yaml
```yaml
---
- name: Generate multiple text files from template
  hosts: all
  vars:
    files:
      - name: file1
        data:
          key1: value1
          key2: value2
      - name: file2
        data:
          key1: value3
          key2: value4
  tasks:
    - name: Generate text files
      template:
        src: template.j2
        dest: "/path/to/{{ item.name }}"
        mode: "0644"
      loop: "{{ files }}"
```

In this example, the playbook uses a variable ```files``` that contains a list of dictionaries, where each dictionary contains the name of the file to be generated and the data to be used in the template. The ```template``` module is then used with a loop over the ```files``` variable to generate the text files. The ```src``` parameter specifies the name of the Jinja2 template file, the ```dest``` parameter specifies the path and filename for the generated text file, and the ```mode``` parameter specifies the file permissions for the generated file.

You would need to create a Jinja2 template file named ```template.j2``` that contains the logic for generating the text files. In the example above, the template file could look like this:vbnet
```vbnet
This is a text file named {{ item.name }}.

Key1: {{ item.data.key1 }}
Key2: {{ item.data.key2 }}
```

This would generate two text files named ```file1``` and ```file2``` in the directory specified by the ```dest``` parameter, each with the corresponding data values for ```key1``` and ```key2```.

