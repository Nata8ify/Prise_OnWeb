/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Deleting Cornfirmation Function
function delE(eventId, userId) {
    var confirmBool = confirm("Do you want to remove this event? (This action can't be undo.)");
    if (confirmBool) {
        window.location.replace("DeleteEvent?eventid=" + eventId + "&userid=" + userId);
    }
}

$("document").ready(function () {
    $(".btn-to-edit").on("click", function () {
        var eventId = $(this).attr('eventId');
        var userId = $(this).attr('userId');
        var event = $(this).attr('event');
        var eventDesc = $(this).attr('eventDesc');
        $("#eventEditorModal").modal("show");
        $(".modal-body #edEventName").val(event);
        $(".modal-body #edEventDesc").val(eventDesc);
        $(".modal-footer #edEventId").val(eventId);
        $(".modal-footer #edUserId").val(userId);
    });
});

$("document").ready(function () {
    $(".btn-to-share").on("click", function () {
        var eventId = $(this).attr('eventId');
        var userId = $(this).attr('userId');
        $("#eventSharingModal").modal("show");
        $(".modal-footer #shrEventId").val(eventId);
        $(".modal-footer #shrUserId").val(userId);
    });
});

$('#shrEventWith').tokenfield({
    delimiter : " "
});
