package com.example.di;

class MovieFinder {
}

// 基于 Setter 方法依赖注入
public class SimpleMovieLister {
  private MovieFinder movieFinder;
  private String movieName;

  public void setMovieFinder(MovieFinder movieFinder) {
    this.movieFinder = movieFinder;
  }

  public void setMovieName(String movieName){
    this.movieName = movieName;
  }
}
