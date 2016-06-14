package gurstudio.com.taleitapp.views.taleit;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import gurstudio.com.taleitapp.model.taleit.Paragraph;

public class ParagraphView extends LinearLayout {
    private Paragraph paragraph;

    public ParagraphView(Context context, AttributeSet attrs) { super(context, attrs); }

    public void setParagraph(Paragraph paragraph){ this.paragraph = paragraph; }
}