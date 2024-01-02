package com.sms.studentSystem.dto;

import java.util.List;

import com.sms.studentSystem.util.enums.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse<T> {

    private List<T>  data;
    private ResponseMessage responseMessage;
    private Long responseCode;
    private String errorMessage;
 	
	 public static class Builder<T> {
		 private List<T>  data;
		    private ResponseMessage responseMessage;
		    private Long responseCode;
		    private String errorMessage;

	        public Builder<T> data(List<T>  data) {
	            this.data = data;
	            return this;
	        }

	        public Builder<T> responseMessage(ResponseMessage responseMessage) {
	            this.responseMessage = responseMessage;
	            return this;
	        }

	        public Builder<T> responseCode(Long responseCode) {
	            this.responseCode = responseCode;
	            return this;
	        }
	        public Builder<T> errorMessage(String errorMessage) {
	            this.errorMessage = errorMessage;
	            return this;
	        }

	        public GenericResponse<T> build() {
	            GenericResponse<T> response = new GenericResponse<>();
	            response.setData(this.data);
	            response.setResponseMessage(this.responseMessage);
	            response.setResponseCode(this.responseCode);
	            response.setErrorMessage(this.errorMessage);
	            return response;
	        }
	    }

	public void setData( List<T>  data) {
		this.data = data;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public void setResponseCode(Long responseCode) {
		this.responseCode = responseCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	
	
	
	
	
	
    
    
}
