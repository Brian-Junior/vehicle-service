package vehicle.service.controller.error;


	import java.time.ZonedDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.NoSuchElementException;

	import org.springframework.http.HttpStatus;
	import org.springframework.web.bind.annotation.ExceptionHandler;
	import org.springframework.web.bind.annotation.ResponseStatus;
	import org.springframework.web.bind.annotation.RestControllerAdvice;
	import org.springframework.web.context.request.ServletWebRequest;
	import org.springframework.web.context.request.WebRequest;

	import lombok.Data;
	import lombok.extern.slf4j.Slf4j;

	@RestControllerAdvice
	@Slf4j
	public class GlobalErrorHandler {
		private enum LogStatus {
			STACK_TRACE, MESSAGE_ONLY
		}

		@Data
		private class ExceptionMessage {
			private String messages;
			private String statusReason;
			private int statusCode;
			private String timeStamp;
			private String uri;

		}
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
		public ExceptionMessage handelNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {
			return buildExceptionMessaage(ex, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
			
		}
	private ExceptionMessage buildExceptionMessaage(NoSuchElementException ex, HttpStatus status, WebRequest webRequest,
			LogStatus logStatus) {
		String message = ex.toString();
		String statusReason = status.getReasonPhrase();
		int statusCode = status.value();
		String uri = null;
		String timeStamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
		
		if(webRequest instanceof ServletWebRequest swr) {
			uri = swr.getRequest().getRequestURI();
		}
		if(logStatus == LogStatus.MESSAGE_ONLY) {
			log.error("Exception: {}", ex.toString());
		}else {
			log.error("Exception: ", ex);
		}
		
		ExceptionMessage excMsg = new ExceptionMessage();
		
		excMsg.setMessages(message);
		excMsg.setStatusCode(statusCode);
		excMsg.setStatusReason(statusReason);
		excMsg.setTimeStamp(timeStamp);
		excMsg.setUri(uri);
		
		return excMsg;
		
	}

	}


