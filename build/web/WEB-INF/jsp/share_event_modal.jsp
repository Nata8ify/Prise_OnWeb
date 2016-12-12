<%-- 
    Document   : create_event_modal
    Created on : 23 ต.ค. 2559, 16:55:11
    Author     : QuartierLatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="eventSharingModal">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Event Sharing</h4>
        </div>
        <form action="ShareEvent" method="post">
            <div class="modal-body">
                <div class="row">
                    <div class="col-sm-12">
                        <div class="form-group">
                            <label for="shrEventWith">Who can see this event?</label>
                            <input id="shrEventWith" name="shareWithUsr"  class="form-control" placeholder="Use ',' in the front of next person's username. "/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <input  type="hidden" id="shrEventId" name="eventId"/>
                <input  type="hidden" id="shrUserId" name="byUserId"/>
                <input  type="submit" class="btn btn-primary"  value="ADD">
            </div>
        </form>
    </div>
</div>
