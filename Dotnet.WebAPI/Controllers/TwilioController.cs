using Microsoft.AspNetCore.Mvc;

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
    public void Post()
    {
        var to = Request.Form["To"].ToString();
        var from = Request.Form["From"].ToString();
        var body = Request.Form["Body"].ToString();
        _logger.LogDebug($"You sent the message '{body}' from {from} to {to}");
        
    }
}
