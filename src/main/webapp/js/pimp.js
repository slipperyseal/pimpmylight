
function updateImages(lights) {
    for (var i = 0; i < lights.length; i++) {
        var light = lights[i];
        document.getElementById(light.name).src = "images/" + light.name + "-" + light.illuminated + ".jpg";
    }
}

function pollForLights() {
    $.ajax({
        url: "service/json/watch",
        //force to handle it as text
        dataType: "text",
        success: function(data) {
            var json = $.parseJSON(data);
            if (json.railwaySignal != null) {
                updateImages(json.railwaySignal);
            }
            pollForLights();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            pollForLights();
        }
    });
}

function statusLights() {
    $.ajax({
        url: "service/json/status",
        //force to handle it as text
        dataType: "text",
        success: function(data) {
            var json = $.parseJSON(data);
            if (json.railwaySignal != null) {
                updateImages(json.railwaySignal);
            }
        }
    });
}

function updateLights(color) {
    $.ajax({
        url: "?update=" + color,
        //force to handle it as text
        dataType: "text",
        success: function(data) {
            statusLights();
        }
    });
}

$(document).ready(function() {
//    $('lightimage').click(function() {
//        updateLights(this.id);
//    });

    pollForLights();
})
