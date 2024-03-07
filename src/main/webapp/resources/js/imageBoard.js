let files = {};
let previewIndex = 0;
let deletefiles = {};
let step = 0;
let deleteNo = 0;
const token = $("meta[name='_csrf']").attr("content");
const header = $("meta[name='_csrf_header']").attr("content");
let fileNum = 0;

function addPreview(input) {
    if (input[0].files.length <= (5 - ($('.preview-box').length))) {
        for (let fileIndex = 0; fileIndex < input[0].files.length; fileIndex++) {
            const file = input[0].files[fileIndex];

            if (validation(file.name))
                continue;

            setPreviewForm(file);
        }
    } else
        alert('5장만 업로드 가능합니다.');
}

function setPreviewForm(file, img) {
    let reader = new FileReader();
    reader.onload = function (img) {
        let imgNum = ++step;

        $("#preview").append(
            "<div class=\"preview-box\" id=\"newImg\" value=\"" + imgNum + "\">" +
            "<img class=\"thumbnail\" id=\"imgName\" src=\"" + img.target.result + "\"\/>" +
            "<p>" + file.name + "</p>" +
            "<a href=\"#\" value=\"" + imgNum + "\" onclick=\"deletePreview(this)\">" +
            "삭제" + "</a>"
            + "</div>"
        );
        files[fileNum] = file;
        fileNum++;
    };
    reader.readAsDataURL(file);
}


function deleteOldPreview(obj) {
    const imgNum = obj.attributes['value'].value;
    const imgName = $("#preview .preview-box[value=" + imgNum + "] .thumbnail").attr("src");
    const idx = imgName.lastIndexOf('=');
    const deleteImg = imgName.substring(idx + 1);

    deletefiles[deleteNo] = deleteImg;
    deleteNo++;

    $("#preview .preview-box[value=" + imgNum + "]").remove();
}

function deletePreview(obj) {
    const imgNum = obj.attributes['value'].value;
    delete files[imgNum];

    $("#preview .preview-box[value=" + imgNum + "]").remove();
    resizeHeight();
}

function validation(fileName) {
    fileName = fileName + "";
    const fileNameExtensionIndex = fileName.lastIndexOf('.') + 1;
    const fileNameExtension = fileName.toLowerCase().substring(
        fileNameExtensionIndex, fileName.length);
    if (!((fileNameExtension === 'jpg')
            || (fileNameExtension === 'gif') || (fileNameExtension === 'png') || (fileNameExtension === 'jpeg'))) {
        alert('jpg, gif, png 확장자만 업로드 가능합니다.');
        return true;
    } else {
        return false;
    }
}

$(document).ready(function () {
    const imageModifyNo = $("#imageModifyNo").val();

    if(imageModifyNo != undefined){
        $.getJSON("/imageBoard/attachList", {imageNo: imageModifyNo}, function (arr) {

            $(arr).each(function (i, attach) {
                $("#preview").append(
                    "<div class=\"preview-box\" value=\"" + attach.imageStep + "\">" +
                        "<img class=\"thumbnail\" id=\"imgName\" src=\"/imageBoard/display?image=" + attach.imageName + "\"\/>" +
                        "<p>" + attach.oldName + "</p>" +
                        "<a href=\"#\" value=\"" + attach.imageStep + "\" onclick=\"deleteOldPreview(this)\">" +
                            "삭제" +
                        "</a>" +
                    "</div>"
                );
                step = attach.imageStep;
            });
        })
            .fail(function (err) {
                alert(err.responseText);
            })
    }


    $("#imageModify").click(function () {
        console.log("imageModify");
        const form = $('#uploadForm')[0];
        let formData = new FormData(form);

        for (let index = 0; index < Object.keys(files).length; index++)
            formData.append('files', files[index]);

        for (let index = 0; index < Object.keys(deletefiles).length; index++)
            formData.append('deleteFiles', deletefiles[index]);


        $.ajax({
            type: 'patch',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            url: '/imageBoard/imageModifyProc',
            data: formData,
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function (result) {

                if (result === -2) {
                    alert("파일이 10MB를 초과하였습니다.");
                } else if(result == 1) {
                    location.href = "/imageBoard/imageList";
                } else if(result == 0 || result == -1){
                    alert("오류가 발생했습니다.\n문제가 계속되면 관리자에게 문의해주세요.");
                }

            }
        });
    });

    $("#imageInsert").on('click', function () {
        const form = $('#uploadForm')[0];
        let formData = new FormData(form);

        for (let index = 0; index < Object.keys(files).length; index++)
            formData.append('files', files[index]);

        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            cache: false,
            url: '/imageBoard/imageInsertProc',
            data: formData,
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function(data){
                if(data == -2)
                    alert("파일이 10MB를 초과하였습니다.");
                else if(data == 1)
                    location.href = "/imageBoard/imageList";
                else if(data == 0 || data == -1)
                    alert("오류가 발생했습니다.\n문제가 계속되면 관리자에게 문의해주세요.");

            },
            error: function (request, status, error) {
                alert("code : " + request.status + "\n"
                    + "message : " + request.responseText + "\n"
                    + "error : " + error);
            }
        });
    });

    $('#attach input[type=file]').change(function () {
        addPreview($(this));
    });
});


$(function (){
    $("#insertBtn").click(function() {
        location.href = "/imageBoard/imageInsert";
    })
})

$(function () {
    $("#modify").click(function () {
        const imageNo = $("#imageNo").val();

        location.href = "/imageBoard/imageModify?imageNo=" + imageNo;
    })
});

$(function () {
    $("#delete").click(function () {
        const imageNo = $("#imageNo").val();

        $.ajax({
            url: '/imageBoard/imageDelete/' + imageNo,
            method: 'delete',
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            success: function(result){
                if(result == 1)
                    location.href= '/imageBoard/imageList';
                else if(result == 0)
                    alert('게시글 삭제에 실패했습니다.\n 문제가 계속 발생하면 관리자에게 문의 부탁드립니다.');
                else if(result == -1)
                    location.href = '/accessError';
            }
        })
    })
});


