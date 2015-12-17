/**
 * Created by robin on 17/12/15.
 */

var bus = new EventBus("http://localhost:4000/eventbus");

bus.onopen = function () {
    bus.registerHandler("chat.to.server", function (err, msg) {
        $('#m').append(msg.body + "\n");
    });
};

function send(event) {
    if (event.keyCode == 13 || event.which == 13) {
        var message = "derp";
        alert(message);
        if (message.length > 0) {
            bus.publish("chat.to.server", {"theMessage": message});
            $('#m').val("");
        }
    }
}