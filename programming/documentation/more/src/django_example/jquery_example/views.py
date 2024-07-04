import json

from django.http import JsonResponse
from django.shortcuts import render

from django.views.decorators.csrf import csrf_exempt


def ajax(request):
  return render(request, 'ajax.html')


def getdata(request):
  return JsonResponse({'content': 'New content'})


@csrf_exempt
def postdata(request):
  if request.method == 'POST':
    name = request.POST['name']
    age = request.POST['age']
    new_content = str(name) + ' ' + str(age)
    return JsonResponse({'content': new_content})
