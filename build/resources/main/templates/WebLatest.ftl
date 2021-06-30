<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<title> Latest </title>
	<link rel="stylesheet" href="/css/gStyle.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/atom-one-dark.min.css">
    <link rel="shortcut icon" href="/images/titleIcon.png" type="image/x-icon">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
    <body>
      <div id="wrapper">
        <#list CodeHolders as CodeHolder>
        <span id="load_date">${CodeHolder.dateFormatted}</span>
        <pre id="code_snippet"><code>${CodeHolder.code}</code></pre>
        </#list>
      </div>
    </body>
</html>