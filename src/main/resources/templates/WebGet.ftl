<!DOCTYPE html>
<html lang="en">
<head>
    <title> Code </title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/css/gStyle.css">
    <link rel="stylesheet" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/atom-one-dark.min.css">
    <link rel="shortcut icon" href="/images/titleIcon.png" type="image/x-icon">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
    <body>
      <div id="wrapper">
        <span id="load_date">${CodeHolder.dateFormatted}</span>
        <#if CodeHolder.viewsRestriction != 0 || CodeHolder.toRemove == true>
        <br>
        <span id="views_restriction">${CodeHolder.viewsRestriction} more views allowed</span>
        </#if>
        <br>
        <#if CodeHolder.timeRestriction != 0>
        <span id="time_restriction">This code will be available for ${CodeHolder.timeRestriction} seconds</span>
        </#if>
        <pre id="code_snippet"><code>${CodeHolder.code}</code></pre>
      </div>
    </body>
</html>
