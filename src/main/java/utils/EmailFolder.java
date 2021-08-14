package utils;



public enum EmailFolder {
		    INBOX("INBOX"),
		    SPAM("SPAM");
		 
		    private String text;
		 
		    private EmailFolder(String text){
		      this.text = text;
		    }
		 
		    public String getText() {
		      return text;
		    }
		  }

