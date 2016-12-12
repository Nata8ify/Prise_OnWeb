<%-- 
    Document   : create_event_modal
    Created on : 23 ต.ค. 2559, 16:55:11
    Author     : QuartierLatin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="modal fade" id="createEventModal">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Create New Event</h4>
                </div>
                <form action="CreateNewEvent?asuser=${user.userId}" method="post">
                    <div class="modal-body">

                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="eventName">What is a title of this event?</label>
                                    <input id="eventName" name="title" required autofocus="" type="text" class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group">
                                    <label for="eventDesc">What is this event about?</label>
                                    <textarea id="eventDesc" name="desc" cols="30" rows="5" class="form-control"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <input  type="submit" class="btn btn-primary"  value="CREATE THIS EVENT NOW">
                        <input  type="reset" class="btn btn-danger" value="RESET FORM">
                        <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
                    </div>
                </form>
            </div>
        </div>
