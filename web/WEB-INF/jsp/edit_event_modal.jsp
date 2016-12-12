<%-- 
    Document   : create_event_modal
    Created on : 23 ต.ค. 2559, 16:55:11
    Author     : QuartierLatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="eventEditorModal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Event Editor</h4>
        </div>
        <form action="EditEvent" method="post">
            <div class="modal-body">

                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="edEventName">What is a title of this event?</label>
                            <input id="edEventName" name="title" required autofocus="" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="edEventDesc">What is this event about?</label>
                            <textarea id="edEventDesc" name="desc" cols="30" rows="5" class="form-control"></textarea>
                        </div>
                    </div>
                </div>

            </div>
            <div class="modal-footer">
                <input  type="hidden" id="edEventId" name="eventId"/>
                <input  type="hidden" id="edUserId" name="userId"/>
                <input  type="submit" class="btn btn-primary"  value="MAKE THE CHANGE">
                <input  type="reset" class="btn btn-danger" value="RESET FORM">
                <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
            </div>
        </form>
    </div>
</div>
