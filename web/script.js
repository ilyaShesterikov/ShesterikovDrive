function newFolder(){
    document.getElementById("enterNameFolder").style.display = "block";
    document.getElementById("opacityBackground").style.display = "block";
}

function newFolderClose(){
    document.getElementById("enterNameFolder").style.display = "none";
    document.getElementById("opacityBackground").style.display = "none";
}

function newFile(){
    document.getElementById("uploadFile").style.display = "block";
    document.getElementById("opacityBackground").style.display = "block";
}

function newFileClose(){
    document.getElementById("uploadFile").style.display = "none";
    document.getElementById("opacityBackground").style.display = "none";
}

function showFileMenu(obj){
    if(obj.nextElementSibling.style.display == "flex"){
        obj.nextElementSibling.style.display = "none";
        return;
    }
    for (var i = 0; i < document.getElementsByClassName("fileMenu").length; i++) {
        document.getElementsByClassName("fileMenu")[i].style.display = "none";
    }
    obj.nextElementSibling.style.display = "flex";
}

function showDeleteFolder(obj){
    if(obj.nextElementSibling.nextElementSibling.style.display == "block"){
        obj.nextElementSibling.nextElementSibling.style.display = "none";
        return;
    }
    for (var i = 0; i < document.getElementsByClassName("deleteFolder").length; i++) {
        document.getElementsByClassName("deleteFolder")[i].style.display = "none";
    }
    obj.nextElementSibling.nextElementSibling.style.display = "block";
}