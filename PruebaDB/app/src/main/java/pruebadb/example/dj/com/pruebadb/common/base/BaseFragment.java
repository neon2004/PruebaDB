package pruebadb.example.dj.com.pruebadb.common.base;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;


import org.androidannotations.annotations.EFragment;

@EFragment
public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();


    public void showSnackbar(final String msg) {
        final Snackbar snackbar = Snackbar.make(this.getView(), msg, Snackbar.LENGTH_LONG);

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                snackbar.dismiss();
            }
        });
//        snackbar.setActionTextColor(ContextCompat.getColor(getActivity(), R.color.aquamarine));
        final View sbView = snackbar.getView();
        final TextView textView = (TextView) sbView.findViewById(android.support.design.R.id
                .snackbar_text);
//        textView.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));

        snackbar.show();
    }
}
