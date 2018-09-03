<%--&#45;&#45;
  Created by IntelliJ IDEA.
  User: Илья
  Date: 23.07.2018
  Time: 1:34
  To change this template use File | Settings | File Templates.
&#45;&#45;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<head>
    <title>Storage|ShesterikovDrive</title>
    <link href="styles/styles.css" rel="stylesheet">
    <script src="script.js" type="text/javascript"></script>
    <script>

    </script>
</head>
<body>
<div>
    <form><button class="back" formaction="ParentFolder">&larr;Back</button></form>
    <div style="display: inline-list-item">
        <a href="signup.jsp">Registration</a>
        <a href="index.html">Log In</a>
    </div>
    <div>
        <c:out value="${request.getParameter('openFolderName')}"></c:out>
    </div>
    <button class="buttonNewFolder" value="New Folder" onclick="newFolder()">
        <p>New Folder</p>
    </button>
    <button class="buttonNewFile" value="New File" onclick="newFile()">
        <p>New File</p>
    </button>

    <div id="opacityBackground" class="opacityBackground"></div>
    <div id="enterNameFolder" class="enterNameFolder">
        <form action="NewFolder" method="POST">
            <p>New Folder:</p>
            <input type="text" name="folderName" placeholder="...">
            <br>
            <input type="submit" name="submitbutton" value="Create">
            <input type="reset" name="resetbutton" value="Cancel" onclick="newFolderClose()">
        </form>
    </div>

    <div id="uploadFile" class="uploadFile">
        <form action="UploadFiles" method="POST" enctype='multipart/form-data' accept-charset="windows-1251">
            <p>Upload Files:</p>
            <input type="file" name="file" value="Choose files" size="50">
            <br>
            <input type="submit" value="Upload File" onclick="newFileClose()">
            <input type="reset" name="resetbutton" value="Cancel" onclick="newFileClose()">
        </form>
    </div>

    <h1>Folders</h1>
    <div class="folders">
        <c:forEach var="folder" items="${folders}">
        <form class="folderSpace">
            <button class="folder" onmouseover="showDeleteFolder(this)" value="${folder}" name="openFolderName"
                                   formaction="OpenFolder">
            <c:out value="${folder}"></c:out>
            </button>
            <br>
            <button class="deleteFolder" formaction="DeleteFile" name="fileToDelete" value="${folder}">Delete</button>
        </form>
        </c:forEach>
    </div>

    <h1>Files</h1>
    <div class="files">
        <c:forEach var="file" items="${files}">
        <form class="fileSpace">
            <button class="file" onmouseover="showFileMenu(this)">
                <div class="fileName">
                    <c:out value="${file}"></c:out>
                </div>
            </button>
            <div class="fileMenu">
                <button class="downloadFile" formaction="DownloadFile" name="fileToDownload" value="${file}">Download</button>
                <button class="deleteFile" formaction="DeleteFile" name="fileToDelete" value="${file}">Delete</button>
            </div>
        </form>
        </c:forEach>
    </div>
</div>
</body>
</html>