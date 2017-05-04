package us.bojie.appstorebo.common.font;

import android.content.Context;
import android.graphics.Typeface;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;


public class BojieFont implements ITypeface {

    private static final String TTF_FILE = "iconfont.ttf";
    private static Typeface typeface = null;
    private static HashMap<String, Character> mChars;


    @Override
    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    @Override
    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap<String, Character>();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), v.character);
            }
            mChars = aChars;
        }
        return mChars;
    }

    @Override
    public String getMappingPrefix() {
        return "bojie";
    }

    @Override
    public String getFontName() {
        return "bojie";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public int getIconCount() {
        return mChars.size();
    }

    @Override
    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList<String>();
        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    @Override
    public String getAuthor() {
        return "bojie.us";
    }

    @Override
    public String getUrl() {
        return "https://bojie.us/";
    }

    @Override
    public String getDescription() {
        return "The premium icon font for Ionic Framework.";
    }

    @Override
    public String getLicense() {
        return "MIT Licensed";
    }

    @Override
    public String getLicenseUrl() {
        return "https://bojie.us/";
    }

    @Override
    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), TTF_FILE);
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }





    public enum Icon implements IIcon {

        download('\ue600'),
        head('\ue61b'),
        shutdown('\ue68e');

        char character;

        Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return character;
        }

        public String getName() {
            return name();
        }

        // remember the typeface so we can use it later
        private static ITypeface typeface;

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new BojieFont();
            }
            return typeface;
        }
    }


}
