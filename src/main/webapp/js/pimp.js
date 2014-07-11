
function updateImages(lights) {
    if (lights != null) {
        for (var i = 0; i < lights.length; i++) {
            var light = lights[i];
            document.getElementById(light.name).src = "images/" + light.name + "-" + light.illuminated + ".jpg";
        }
    }
}

function changeLight(color) {
    $.ajax({
        url: "service/json/change",
        type: "POST",
        data: '{ "name": "' + color + '"}',
        dataType: "text",
        success: function(data) {
            updateImages( $.parseJSON(data) );
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}

function watchLights() {
    $.ajax({
        url: "service/json/watch",
        dataType: "text",
        success: function(data) {
            updateImages( $.parseJSON(data) );
            watchLights();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            watchLights();
        }
    });
}

$(document).ready(function() {
    watchLights();
})
