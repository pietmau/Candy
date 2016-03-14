package com.candyspace.androidtest;

import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.candyspace.androidtest.api.Article;
import com.candyspace.androidtest.holders.GenericHolder;
import com.candyspace.androidtest.holders.HeroViewHolder;
import com.candyspace.androidtest.holders.SampleViewHolder;
import com.candyspace.androidtest.model.ArticleWrapper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample RecyclerView adapter.
 */
public class SampleRecyclerViewAdapter extends RecyclerView.Adapter<GenericHolder> {
    public static final int NORMAL = 0;
    public static final int HERO = 1;

    @IntDef({NORMAL, HERO})
    public @interface ArticleType {
    }

    private final List<Article> articles = new ArrayList<>();

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
            return new SampleViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_item, parent, false);
            return new HeroViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int position) {
        int articleToGet;
        if (position % 5 == 0) {
            // 0 and multiples of 5 are heroes, we get article 0, or 1, or 2...
            articleToGet = position / 5;
        } else {
            // not heroes, we get the corresponding not hero article
            articleToGet = ((articles.size() - 1) / 5) - (position / 5) + position;
        }
        Article article = articles.get(articleToGet);
        holder.title.setText(article.getTitle());
        holder.body.setText(Html.fromHtml(article.getBody()));

        ArticleWrapper articleWrapper = new ArticleWrapper(article);

        if (articleWrapper.getThumbImageUrl() != null) {//Possible bug here, it was: articleWrapper.getHeroImageUrl()!=null
            Picasso.with(holder.itemView.getContext()).load(articleWrapper.getThumbImageUrl()).into(holder.image);
        } else {
            holder.image.setImageBitmap(null);
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles) {
        this.articles.clear();
        if (articles != null) {
            this.articles.addAll(articles);
        }
        notifyDataSetChanged();
    }

    @ArticleType
    @Override
    public int getItemViewType(int position) {
        if (position % 5 == 0) return HERO;
        else return NORMAL;
    }
}
