
function updateImages(lights) {
    if (lights != null) {
        for (var i = 0; i < lights.length; i++) {
            var light = lights[i];
            document.getElementById(light.name).src = "images/" + light.name + "-" + light.illuminated + ".jpg";
        }
    }
}

function pollForLights() {
    $.ajax({
        url: "service/json/watch",
        dataType: "text",
        success: function(data) {
            updateImages( $.parseJSON(data) );
            pollForLights();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            pollForLights();
        }
    });
}

function updateLights(color) {
    $.ajax({
        url: "service/json/update",
        method: "POST",
        data: '{ "name": "' + color + '"}',
        dataType: "text",
        success: function(data) {
            updateImages( $.parseJSON(data) );
        },
        error: function (jqXHR, textStatus, errorThrown) {
        }
    });
}

$(document).ready(function() {
    pollForLights();
})
