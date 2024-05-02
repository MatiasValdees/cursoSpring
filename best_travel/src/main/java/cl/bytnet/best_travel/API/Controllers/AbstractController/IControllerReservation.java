package cl.bytnet.best_travel.API.Controllers.AbstractController;

import cl.bytnet.best_travel.API.Controllers.AbstractController.IController;
import cl.bytnet.best_travel.API.Model.Request.ReservationRequest;

import java.util.UUID;

public interface IControllerReservation extends IController<ReservationRequest, UUID> {
}
