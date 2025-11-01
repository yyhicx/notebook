from django.urls import path, include
import django_eventstream
from . import views

urlpatterns = [
    path('web-workers', views.web_workers),
    path('server-sent-events', views.server_sent_events),
    path('events/', include(django_eventstream.urls), {'channels': ['time']}),
    path('chat', views.chat),
    path('<str:room_name>/', views.chat_room),
    path('use-requirejs', views.use_requirejs),
]
