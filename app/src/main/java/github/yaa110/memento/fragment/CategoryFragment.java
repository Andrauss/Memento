package github.yaa110.memento.fragment;

import android.view.View;

import github.yaa110.memento.R;
import github.yaa110.memento.adapter.NoteAdapter;
import github.yaa110.memento.adapter.template.ModelAdapter;
import github.yaa110.memento.fragment.template.RecyclerFragment;
import github.yaa110.memento.inner.Animator;
import github.yaa110.memento.model.DatabaseModel;
import github.yaa110.memento.model.Note;

public class CategoryFragment extends RecyclerFragment<Note, NoteAdapter> {
	public View protector;
	public View fab_type;
	public View fab_drawing;
	public boolean isFabOpen = false;

	private ModelAdapter.ClickListener listener = new ModelAdapter.ClickListener() {
		@Override
		public void onClick(DatabaseModel item, int position) {
			// TODO
		}

		@Override
		public void onChangeSelection(boolean haveSelected) {
			toggleSelection(haveSelected);
		}

		@Override
		public void onCountSelection(int count) {
			onChangeCounter(count);
		}
	};

	public CategoryFragment() {}

	@Override
	public void init(View view) {
		protector = view.findViewById(R.id.protector);
		fab_type = view.findViewById(R.id.fab_type);
		fab_drawing = view.findViewById(R.id.fab_drawing);

		protector.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				toggleFab(true);
			}
		});

		fab_type.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO
			}
		});

		fab_drawing.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				// TODO
			}
		});
	}

	@Override
	public void onClickFab() {
		toggleFab(false);
	}

	public void toggleFab(boolean forceClose) {
		if (isFabOpen) {
			isFabOpen = false;

			Animator.create(getContext())
				.on(protector)
				.setEndVisibility(View.GONE)
				.animate(R.anim.fade_out);

			Animator.create(getContext())
				.on(fab)
				.animate(R.anim.fab_rotate_back);

			Animator.create(getContext())
				.on(fab_type)
				.setEndVisibility(View.GONE)
				.animate(R.anim.fab_out);

			Animator.create(getContext())
				.on(fab_drawing)
				.setDelay(50)
				.setEndVisibility(View.GONE)
				.animate(R.anim.fab_out);
		} else if (!forceClose) {
			isFabOpen = true;

			Animator.create(getContext())
				.on(protector)
				.setStartVisibility(View.VISIBLE)
				.animate(R.anim.fade_in);

			Animator.create(getContext())
				.on(fab)
				.animate(R.anim.fab_rotate);

			Animator.create(getContext())
				.on(fab_type)
				.setDelay(80)
				.setStartVisibility(View.VISIBLE)
				.animate(R.anim.fab_in);

			Animator.create(getContext())
				.on(fab_drawing)
				.setStartVisibility(View.VISIBLE)
				.animate(R.anim.fab_in);
		}
	}

	@Override
	public int getLayout() {
		return R.layout.fragment_category;
	}

	@Override
	public Class<NoteAdapter> getAdapterClass() {
		return NoteAdapter.class;
	}

	@Override
	public ModelAdapter.ClickListener getListener() {
		return listener;
	}
}
