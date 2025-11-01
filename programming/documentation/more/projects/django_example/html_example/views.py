from django.shortcuts import render
from django_eventstream import get_current_event_id


def web_workers(request):
  return render(request, 'web-workers.html')


def chat(request):
  return render(request, 'chat.html')


def chat_room(request, room_name):
  return render(request, 'chat-room.html', {'room_name': room_name})


def server_sent_events(request):
  # return render(request, 'server_sent_events.html')
  context = {}
  context['url'] = '/html-example/events/'
  context['last_id'] = get_current_event_id(['time'])
  return render(request, 'server-sent-events.html', context)


def use_requirejs(request):
  return render(request, 'use-requirejs.html')
