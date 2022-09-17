package errorhandling;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.logging.Level;
import java.util.logging.Logger;


import static errorhandling.GenericExceptionMapper.gson;

public class InternalErrorExceptionMapper implements ExceptionMapper<InternalErrorException> {
    @Override
    public Response toResponse(InternalErrorException ex) {
        Logger.getLogger(EntityNotFoundExceptionMapper.class.getName()).log(Level.SEVERE, null, ex);
        ExceptionDTO err = new ExceptionDTO(500, ex.getMessage());

        return Response.status(500)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }
}
