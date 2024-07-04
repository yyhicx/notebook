from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from . import views

app_name = 'blog'
urlpatterns = [
    path('articles/', views.article_list),
    path('articles/<int:pk>/', views.article_detail),
]

urlpatterns = format_suffix_patterns(urlpatterns)
