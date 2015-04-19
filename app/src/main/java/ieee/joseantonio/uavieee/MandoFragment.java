package ieee.joseantonio.uavieee;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MandoFragment extends Fragment {


    public static MandoFragment newInstance() {
        MandoFragment fragment = new MandoFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    public MandoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mando, container, false);
    }


}
