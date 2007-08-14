package gov.nih.nci.cgems.web.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 * The FeedbackForm class is the form validator for the Feedback form.
 * <P>
 * @author mholck
 * @see org.apache.struts.action.ActionForm
 */
public class FeedbackForm extends ActionForm
{
	// Serial version ID for serializable
	private static final long serialVersionUID = -8620338418796014946L;
	
	private String likeFeature;
	private String dislikeFeature;
	private String comment;

	/**
	 * The validate method is called when the form is submitted and is
	 * responsible for making sure the input is valid.
	 * <P>
	 * @param mapping The ActionMapping for the posted action
	 * @param request The HttpServletRequest for this post
	 */
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
	{
		ActionErrors errors = new ActionErrors();
		
		// Validate that they have input something
		if ((likeFeature == null) || (likeFeature.length() < 1) &&
			(dislikeFeature == null) || (dislikeFeature.length() < 1) &&
			(comment == null) || (comment.length() < 1))
				errors.add("feedback", new ActionMessage("error.feedback.required"));
		
		return errors;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDislikeFeature() {
		return dislikeFeature;
	}

	public void setDislikeFeature(String dislikeFeature) {
		this.dislikeFeature = dislikeFeature;
	}

	public String getLikeFeature() {
		return likeFeature;
	}

	public void setLikeFeature(String likeFeature) {
		this.likeFeature = likeFeature;
	}
	
}
