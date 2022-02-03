const imageFile = document.getElementById("image");

imageFile.onchange = function () {
    const maxAllowedSize = 300 * 1024;
    if (this.files[0].size > maxAllowedSize) {
        alert("Image File is too big!");
        this.value = "";
    }
    if (this.files[0]==null) {
        alert("upload image!");
        this.value = "";
    }
}

window.onload = function () {
    var form = document.getElementById('form'),
        imageInput = document.getElementById('image');

    form.onsubmit = function () {
        var isValid = /\.jpe?g$/i.test(imageInput.value);
        if (!isValid) {
            alert('Only jpg files allowed!');
        }
        return isValid;
    };
};
