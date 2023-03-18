using Microsoft.AspNetCore.Mvc;
using Twilio;
using Twilio.TwiML;
using System.Collections.Generic;
using Twilio.TwiML.Messaging;

namespace Dotnet.WebAPI.Controllers;

[ApiController]
[Route("[controller]")]
public class TwilioController : ControllerBase
{
    private readonly ILogger<TwilioController> _logger;

    public TwilioController(ILogger<TwilioController> logger)
    {
        _logger = logger;
    }

    // [HttpPost(Name = "PostTwilio")]
    // public void Post([FromUri] string id,[FromUri] string id,[FromUri] string id)
    // {
    // }

    [HttpPost]
    public string Post()
    {
        var to = Request.Form["To"].ToString();
        var from = Request.Form["From"].ToString();
        var body = Request.Form["Body"].ToString();
        _logger.LogDebug($"You sent the message '{body}' from {from} to {to}");
        
        var response = new MessagingResponse();
        var message = new Message();
        message.Body("Hello World!");
        response.Append(message);
        response.Redirect(url: new Uri("https://demo.twilio.com/welcome/sms/"));

        _logger.LogDebug(response.ToString());
        Console.WriteLine();
        return response.ToString();
    }
}
