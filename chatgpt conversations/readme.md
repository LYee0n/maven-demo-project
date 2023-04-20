# ChatGPT Conversations


## export conversation

[ref](https://www.reddit.com/r/ChatGPT/comments/zm237o/save_your_chatgpt_conversation_as_a_markdown_file/)

```javascript
function h(html) { return html.replace(/<p>/g, '\n\n').replace(/<\/p>/g, '').replace(/<b>/g, '**').replace(/<\/b>/g, '**').replace(/<i>/g, '_').replace(/<\/i>/g, '_').replace(/<code[^>]*>/g, (match) => { const lm = match.match(/class="[^"]*language-([^"]*)"/); return lm ? '\n```' + lm[1] + '\n' : '```'; }).replace(/<\/code[^>]*>/g, '```').replace(/<[^>]*>/g, '').replace(/Copy code/g, '').replace(/This content may violate our content policy. If you believe this to be in error, please submit your feedback — your input will aid our research in this area./g, '').trim(); } (()=>{ const e=document.querySelectorAll(".text-base");let t="";for(const s of e)s.querySelector(".whitespace-pre-wrap")&&(t+=`**${s.querySelector('img')?'You':'ChatGPT'}**: ${h(s.querySelector(".whitespace-pre-wrap").innerHTML)}\n\n`);const o=document.createElement("a");o.download="Conversation with ChatGPT.md",o.href=URL.createObjectURL(new Blob([t])),o.style.display="none",document.body.appendChild(o),o.click()})();
```

```javascript
function h(html) { return html.replace(/<p>/g, '\n\n').replace(/<\/p>/g, '').replace(/<b>/g, '**').replace(/<\/b>/g, '**').replace(/<i>/g, '_').replace(/<\/i>/g, '_').replace(/<code[^>]*>/g, (match) => { const lm = match.match(/class="[^"]*language-([^"]*)"/); return lm ? '\n```' + lm[1] + '\n' : '```'; }).replace(/<\/code[^>]*>/g, '```').replace(/<[^>]*>/g, '').replace(/Copy code/g, '').replace(/This content may violate our content policy. If you believe this to be in error, please submit your feedback — your input will aid our research in this area./g, '').trim(); } (()=>{ const e=document.querySelectorAll(".text-base");let t="";for(const s of e)s.querySelector(".whitespace-pre-wrap")&&(t += t == ""?"":"--------\n", t+=`**${s.querySelectorAll('img').length>1?'You':'ChatGPT'}**: ${h(s.querySelector(".whitespace-pre-wrap").innerHTML)}\n\n`);const o=document.createElement("a");o.download=(document.querySelector(".pr-14.bg-gray-800")?.innerText||"Conversation with ChatGPT")+".md",o.href=URL.createObjectURL(new Blob([t])),o.style.display="none",document.body.appendChild(o),o.click()})();
```


```javascript
(() => {
    const msgs = document.querySelectorAll("div.whitespace-pre-wrap");
    const markdowns = Array.from(msgs).map(div => {
        if (div.children.length === 1) {
            // This is a ChatGPT response.
            const mddiv = div.children[0];
            const fiberKey =
                Object.keys(mddiv)
                      .find(k => k.startsWith('__reactFiber$'));
            const origMd = mddiv[fiberKey].return.pendingProps.children;
            return `## ChatGPT\n${origMd}`;
        } else {
            // This is the user.  The website doesn't Markdown-format
            // the user's text, but we assume that the user has been
            // using Markdown if they're running this script.
            // The user's email address is in the alt attribute of
            // their icon, but we don't use that.
            return `## User\n${div.textContent}`;
        }
    });

    const titleMd = `# ${document.title}`;
    markdowns.unshift(titleMd);

    const allMd = markdowns.join('\n\n');

    // I use an ISO 8601 format of the local time.  Since colons aren't
    // allowed in filenames, and everything else looks weird to me,
    // I just use the variant without punctuation.
    const now = new Date();
    const dateString = `${now.getFullYear()}${(now.getMonth() + 1).toString().padStart(2, '0')}${now.getDate().toString().padStart(2, '0')}T${now.getHours().toString().padStart(2, '0')}${now.getMinutes().toString().padStart(2, '0')}${now.getSeconds().toString().padStart(2, '0')}`;

    const fileName = `ChatGPT log - ${document.title} - ${dateString}.md`;
    const downloadLink = document.createElement("a");
    downloadLink.download = fileName;
    downloadLink.href = URL.createObjectURL(new Blob([allMd], { type: "text/markdown" }));
    // On Chrome, it seems that we don't actually need to put the link
    // into the document.
    //downloadLink.style.display = "none";
    //document.body.appendChild(downloadLink);
    downloadLink.click();

    return allMd;
})();
```
