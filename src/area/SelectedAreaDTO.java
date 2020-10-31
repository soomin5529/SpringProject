package area;

import java.io.Serializable;

// 동 클래스
public class SelectedAreaDTO implements Serializable {
	private String si;
	private String sig;
	private String emd;

	public String getSi() {
		return si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getEmd() {
		return emd;
	}

	public void setEmd(String emd) {
		this.emd = emd;
	}

	@Override
	public String toString() {
		return "SelectedAreaDTO[si=" + si + ", sig=" + sig + ", emd=" + emd + "]";
	}

}
