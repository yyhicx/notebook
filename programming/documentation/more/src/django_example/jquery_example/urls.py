from django.urls import path
from . import views

urlpatterns = [
    path('ajax', views.ajax),
    path('getdata', views.getdata),
    path('postdata', views.postdata),
]
